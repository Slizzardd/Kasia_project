import React, { useReducer, useState } from 'react';

import MainLayout from '@/components/layout/MainLayout';
import Form from '@/components/ui/Form';

import styles from './Auth.module.scss';

const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$/;

const Auth = () => {
  const [authType, setAuthType] = useState<'login' | 'register'>('login');

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
            formInfo={{ buttonText: 'Sign in' }}
            initFormState={{
              email: { text: '', placeholder: 'Email', type: 'text' },
              password: { text: '', placeholder: 'Password', type: 'password' },
            }}
            validation={validate}
          />
          <button
            className={styles.anotherMethodButton}
            onClick={() => setAuthType('register')}
          >
            register new account
          </button>
        </>
      ) : (
        <>
          <Form
            key={'register'}
            onSubmit={authSubmit}
            formInfo={{ buttonText: 'Sign up' }}
            initFormState={{
              firstName: { text: '', placeholder: 'First Name', type: 'text' },
              lastName: { text: '', placeholder: 'First Name', type: 'text' },
              email: { text: '', placeholder: 'Email', type: 'text' },
              password: { text: '', placeholder: 'Password', type: 'password' },
              repeatPassword: {
                text: '',
                placeholder: 'Repeat Password',
                type: 'password',
              },
            }}
            validation={validate}
          />
          <button
            className={styles.anotherMethodButton}
            onClick={() => setAuthType('login')}
          >
            already have account
          </button>
        </>
      )}
    </MainLayout>
  );
};

export default Auth;
