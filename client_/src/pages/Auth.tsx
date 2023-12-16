import { observer } from 'mobx-react';
import React, { useState } from 'react';

import MainLayout from '@/components/layout/MainLayout';
import Form from '@/components/ui/Form';
import { useRootModel } from '@/store/rootModel';

import styles from './Auth.module.scss';

const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$/;

const Auth = () => {
  const [authType, setAuthType] = useState<'login' | 'register'>('login');
  const { t } = useRootModel((root) => root.translationService);

  const validate = (str: string, name: string) => {
    switch (name) {
      case 'email': {
        return emailPattern.test(str);
      }
      default: {
        return true;
      }
    }
  };

  const authSubmit = async (formState: { [key: string]: string }) => {};

  return (
    <MainLayout>
      {authType === 'login' ? (
        <>
          <Form
            key={'login'}
            onSubmit={authSubmit}
            formInfo={{ buttonText: t('Sign in') }}
            initFormState={{
              email: { text: '', placeholder: t('Email'), type: 'text' },
              password: { text: '', placeholder: t('Password'), type: 'password' },
            }}
            validation={validate}
          />
          <p>{t('Don`t you have an account?')}</p>
          <button
            className={styles.anotherMethodButton}
            onClick={() => setAuthType('register')}
          >
            {t('Sign up')}
          </button>
        </>
      ) : (
        <>
          <Form
            key={'register'}
            onSubmit={authSubmit}
            formInfo={{ buttonText: t('Sign up') }}
            initFormState={{
              firstName: { text: '', placeholder: t('First Name'), type: 'text' },
              lastName: { text: '', placeholder: t('Last Name'), type: 'text' },
              email: { text: '', placeholder: t('Email'), type: 'text' },
              password: { text: '', placeholder: t('Password'), type: 'password' },
              repeatPassword: {
                text: '',
                placeholder: t('Repeat password'),
                type: 'password',
              },
            }}
            validation={validate}
          />

          <button
            className={styles.anotherMethodButton}
            onClick={() => setAuthType('login')}
          >
            {t('Sign in')}
          </button>
        </>
      )}
    </MainLayout>
  );
};

export default observer(Auth);
