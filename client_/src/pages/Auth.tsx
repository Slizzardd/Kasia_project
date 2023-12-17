import { observer } from 'mobx-react';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import MainLayout from '@/components/layout/MainLayout';
import { useRootModel } from '@/store/rootModel';

import styles from './Auth.module.scss';

const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$/;

const Auth = () => {
  const navigate = useNavigate();
  const [authType, setAuthType] = useState<'login' | 'register'>('login');
  const { t } = useRootModel((root) => root.translationService);
  const { registration, authStore, login } = useRootModel((root) => root.userModel);

  const [formState, setFormState] = useState({
    email: '',
    firstName: '',
    lastName: '',
    password: '',
    repeatPassword: '',
    errorText: '',
  });
  const handleSubmitRegistration = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const { email, password, firstName, lastName } = formState;

    registration({ email, password, firstName, lastName }).then((jwtToken) => {
      if (jwtToken === null) {
        setFormState((prev) => ({
          ...prev,
          errorText: 'Error in registration',
        }));
      } else {
        navigate('/');
      }
    });
  };

  const handleSubmitLogin = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const { email, password } = formState;

    login({ email, password }).then((jwtToken) => {
      if (jwtToken === null) {
        setFormState((prev) => ({
          ...prev,
          errorText: 'Error in login',
        }));
      } else {
        navigate('/');
      }
    });
  };

  return (
    <MainLayout>
      {authType === 'login' ? (
        <>
          <form onSubmit={handleSubmitLogin} className={styles.form}>
            <div className={''}>
              <h2 className={styles.form_title}>{t('Sign in')}</h2>
              <div className={styles.form_box}>
                <label htmlFor={'email'} className={styles.form_label}>
                  {t('Email')}
                </label>
                <input
                  type={'text'}
                  className={styles.form_field}
                  id={'email'}
                  onChange={(e) =>
                    setFormState((prev) => ({
                      ...prev,
                      email: e.target.value,
                      errorText: '',
                    }))
                  }
                  required
                />
              </div>

              <div className={styles.form_box}>
                <label htmlFor={'password'} className={styles.form_label}>
                  {t('Password')}
                </label>
                <input
                  type={'text'}
                  className={styles.form_field}
                  id={'password'}
                  value={formState.password}
                  onChange={(e) =>
                    setFormState((prev) => ({
                      ...prev,
                      password: e.target.value,
                      errorText: '',
                    }))
                  }
                  required
                />
              </div>
              <button type={'submit'} className={styles.form_button}>
                {t('Submit')}
              </button>
              <div>
                <p>
                  {t('Don`t you have an account?')}
                  <button
                    onClick={() => {
                      setAuthType('register');
                    }}
                  >
                    Click!
                  </button>
                </p>
              </div>
            </div>
          </form>
        </>
      ) : (
        <>
          <form onSubmit={handleSubmitRegistration} className={styles.form}>
            <div className={''}>
              <h2 className={styles.form_title}>{t('Sign up')}</h2>
              <div className={styles.form_box}>
                <label htmlFor={'email'} className={styles.form_label}>
                  {t('Email')}
                </label>
                <input
                  type={'text'}
                  className={styles.form_field}
                  id={'email'}
                  onChange={(e) =>
                    setFormState((prev) => ({
                      ...prev,
                      email: e.target.value,
                      errorText: '',
                    }))
                  }
                  required
                />
              </div>

              <div className={styles.form_box}>
                <label htmlFor={'password'} className={styles.form_label}>
                  {t('Password')}
                </label>
                <input
                  type={'text'}
                  className={styles.form_field}
                  id={'password'}
                  value={formState.password}
                  onChange={(e) =>
                    setFormState((prev) => ({
                      ...prev,
                      password: e.target.value,
                      errorText: '',
                    }))
                  }
                  required
                />
              </div>
              <div className={styles.form_box}>
                <label htmlFor={'firstName'} className={styles.form_label}>
                  {t('First Name')}
                </label>
                <input
                  type={'text'}
                  className={styles.form_field}
                  id={'firstName'}
                  value={formState.firstName}
                  onChange={(e) =>
                    setFormState((prev) => ({
                      ...prev,
                      firstName: e.target.value,
                      errorText: '',
                    }))
                  }
                  required
                />
              </div>

              <div className={styles.form_box}>
                <label htmlFor={'lastName'} className={styles.form_label}>
                  {t('Last Name')}
                </label>
                <input
                  type={'text'}
                  className={styles.form_field}
                  id={'lastName'}
                  value={formState.lastName}
                  onChange={(e) =>
                    setFormState((prev) => ({
                      ...prev,
                      lastName: e.target.value,
                      errorText: '',
                    }))
                  }
                  required
                />
              </div>
              <button type={'submit'} className={styles.form_button}>
                {t('Submit')}
              </button>
              <div>
                <p>
                  {t('Do you already have an account?')}
                  <button
                    onClick={() => {
                      setAuthType('register');
                    }}
                  >
                    Click!
                  </button>
                </p>
              </div>
            </div>
          </form>
        </>
      )}
    </MainLayout>
  );
};

export default observer(Auth);
