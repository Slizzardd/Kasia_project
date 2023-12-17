import React, { useState } from 'react';

import styles from './Input.module.scss';

interface InputProps {
  value: string;
  error: string;
  isValid: boolean;
  type: 'text' | 'password';
  placeholder?: string;
  onChange: (str: string) => void;
}

const Input: React.FC<InputProps> = (props) => {
  const [active, setActive] = useState<boolean>(props.value.length > 0);

  const isValid = !props.isValid;
  const isError = props.error.length > 0;

  return (
    <>
      <label
        className={
          isValid
            ? isError
              ? `${styles.label} ${styles.label_error}`
              : `${styles.label} ${styles.label_notValid}`
            : styles.label
        }
      >
        {props.placeholder && (
          <p
            className={
              active
                ? `${styles.placeholder} ${styles.placeholder_active}`
                : styles.placeholder
            }
          >
            {props.placeholder}
          </p>
        )}
        <input
          className={styles.input}
          onFocus={() => setActive(true)}
          onBlur={() => setActive(props.value.length > 0)}
          value={props.value}
          onChange={(e) => props.onChange(e.target.value)}
        />
      </label>
      {isError && <p className={styles.error}>{props.error}</p>}
    </>
  );
};

export default Input;
