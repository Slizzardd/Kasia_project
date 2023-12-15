const changeTheme = (theme: 'light' | 'dark') => {
  document.documentElement.setAttribute('theme', theme);
  localStorage.setItem('CURRENT_THEME', theme);
  return theme;
};

const toggleTheme = () => {
  const storedTheme = localStorage.getItem('CURRENT_THEME');
  if (storedTheme === 'light' || storedTheme === 'dark') {
    return changeTheme(storedTheme === 'light' ? 'dark' : 'light');
  } else {
    const isDarkTheme =
      window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
    return changeTheme(isDarkTheme ? 'dark' : 'light');
  }
};

const initTheme = () => {
  const theme = localStorage.getItem('CURRENT_THEME') as 'light' | 'dark';
  if (theme !== 'light' && theme !== 'dark') {
    const isDarkTheme =
      window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;

    return changeTheme(isDarkTheme ? 'dark' : 'light');
  } else {
    return changeTheme(theme);
  }
};

const getTheme = () => {
  const theme = localStorage.getItem('CURRENT_THEME') as 'light' | 'dark';
  if (theme !== 'light' && theme !== 'dark') {
    const isDarkTheme =
      window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
    return isDarkTheme ? 'dark' : 'light';
  } else {
    return theme;
  }
};

export { changeTheme, getTheme, initTheme, toggleTheme };
