import React from 'react';
import { Link } from 'react-router-dom';

import MainLayout from '@/components/layout/MainLayout';
import { Screens } from '@/routes/AppRouter';

const Contacts = () => {
  return (
    <MainLayout>
      <p>Contacts page</p>

      <Link to={Screens.About}>about</Link>
    </MainLayout>
  );
};

export default Contacts;
