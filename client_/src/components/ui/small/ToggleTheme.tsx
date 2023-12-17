import React, { useState } from 'react';

import { getTheme, toggleTheme } from '@/utils/Theme';

import styles from './ToggleTheme.module.scss';

const ToggleTheme = () => {
  const [theme, setTheme] = useState<'light' | 'dark'>(getTheme());

  const changeThemeHandler = () => {
    setTheme(toggleTheme());
  };

  const getChecked = () => {
    return theme === 'light';
  };
  return (
    <div>
      <input type={'checkbox'} checked={getChecked()} onChange={changeThemeHandler} />
    </div>
  );
};

export default ToggleTheme;
