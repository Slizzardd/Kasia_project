import React, { useReducer } from 'react';

import Input from '@/components/ui/form/Input';

import styles from './Form.module.scss';

interface I_FormStateAction_change {
  type: 'change';
  payload: { text: string; name: string; isValid: boolean };
}

interface I_FormStateAction_error {
  type: 'error';
  payload: { text: string; name: string; isValid?: boolean };
}

interface I_FormStateAction_clear {
  type: 'clear';
}

type I_FromStateActions =
  | I_FormStateAction_change
  | I_FormStateAction_error
  | I_FormStateAction_clear;

interface I_FormState {
  [key: string]: I_FormValue;
}

interface I_FormValue {
  text: string | boolean;
  error: string;
  isValid: boolean;
}

const createFormState = (init: { [key: string]: InitFormStateValue }): I_FormState => {
  const formState: { [key: string]: I_FormValue } = {};

  Object.keys(init).forEach((key) => {
    const item = init[key];
    formState[key] = { text: item.text, error: '', isValid: true };
  });

  return formState;
};

const formReducer = (state: I_FormState, action: I_FromStateActions): I_FormState => {
  switch (action.type) {
    case 'change': {
      return {
        ...state,
        [action.payload.name]: {
          text: action.payload.text,
          isValid: action.payload.isValid,
          error: '',
        },
      };
    }
    case 'error': {
      const newState: I_FormState = {};

      Object.keys(state).forEach((stateKey) => {
        const item = state[stateKey];

        if (stateKey === action.payload.name) {
          newState[stateKey] = {
            text: item.text,
            error: action.payload.text,
            isValid: action.payload.isValid ?? item.isValid,
          };
        } else {
          newState[stateKey] = item;
        }
      });

      return newState;
    }
    case 'clear': {
      const newState: I_FormState = {};
      Object.keys(state).forEach((stateKey) => {
        newState[stateKey] = { isValid: true, error: '', text: '' };
      });
      return state;
    }
    default: {
      return state;
    }
  }
};

interface InitFormStateValue_text {
  text: string;
  placeholder: string;
  type: 'text' | 'password';
}
interface InitFormStateValue_checkbox {
  text: boolean;
  placeholder: string;
  type: 'checkbox';
}

type InitFormStateValue = InitFormStateValue_text | InitFormStateValue_checkbox;

interface FormProps {
  initFormState: {
    [key: string]: InitFormStateValue;
  };
  validation: (str: string, name: string) => boolean;
  formInfo: {
    buttonText: string;
    title?: string;
    description?: string;
  };
  onSubmit: (formState: { [key: string]: string }) => Promise<void>;
}

const Form: React.FC<FormProps> = (props) => {
  const [formState, dispatchForm] = useReducer(
    formReducer,
    createFormState(props.initFormState),
  );

  const submit = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    e.preventDefault();

    const submitFormInfo: { [key: string]: string } = {};

    Object.keys(formState).forEach((key) => {
      submitFormInfo[key] = String(formState[key].text);
    });

    props
      .onSubmit(submitFormInfo)
      .then(() => {
        dispatchForm({ type: 'clear' });
      })
      .catch((err: { name: string; text: string } | null) => {
        if (err !== null) {
          dispatchForm({ type: 'error', payload: { text: err.text, name: err.name } });
        }
      });
  };

  return (
    <form className={styles.form}>
      {(props.formInfo.title || props.formInfo.description) && (
        <div className={styles.form_info}>
          {props.formInfo.title && (
            <h2 className={styles.form_title}>{props.formInfo.title}</h2>
          )}
          {props.formInfo.description && (
            <p className={styles.form_description}>{props.formInfo.description}</p>
          )}
        </div>
      )}

      {Object.keys(formState).map((key, index) => {
        const input = formState[key];
        const info = props.initFormState[key];

        if (info.type === 'text' || info.type === 'password') {
          return (
            <Input
              key={index}
              value={input.text as string}
              error={input.error}
              isValid={input.isValid}
              placeholder={info.placeholder}
              type={info.type}
              onChange={(str) =>
                dispatchForm({
                  type: 'change',
                  payload: { text: str, name: key, isValid: props.validation(str, key) },
                })
              }
            />
          );
        } else {
          return false;
        }
      })}

      <button className={styles.form_button} onClick={submit}>
        {props.formInfo.buttonText}
      </button>
    </form>
  );
};

export default Form;
