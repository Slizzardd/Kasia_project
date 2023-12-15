import React, { useEffect } from 'react';

import Header from '@/components/layout/Header';
import AppRouter from '@/routes/AppRouter';
import { initTheme } from '@/utils/Theme';

function Main() {
  useEffect(() => {
    initTheme();
  }, []);

  return (
    <>
      <Header />
      <AppRouter />
    </>
  );
}

export default Main;
