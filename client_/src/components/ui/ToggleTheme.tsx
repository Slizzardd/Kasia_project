import React, { useState } from 'react';

import { getTheme, toggleTheme } from '@/utils/Theme';

import styles from './ToggleTheme.module.scss';

const ToggleTheme = () => {
  const [theme, setTheme] = useState<'light' | 'dark'>(getTheme());

  const changeThemeHandler = () => {
    setTheme(toggleTheme());
  };

  return (
    <button onClick={changeThemeHandler} className={styles.button}>
      {theme}
    </button>
  );
};

export default ToggleTheme;
