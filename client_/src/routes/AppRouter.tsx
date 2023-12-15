import React from 'react';
import { Route, Routes } from 'react-router-dom';

import About from '@/pages/About';
import Auth from '@/pages/Auth';
import Contacts from '@/pages/Contacts';
import Home from '@/pages/Home';
import Profile from '@/pages/Profile';

export enum Screens {
  'Home' = '/',
  'About' = '/about',
  'Contacts' = '/contacts',
  'Auth' = '/auth',
  'Profile' = '/profile',
}

interface I_Page {
  path: Screens;
  element: React.FC;
  needIsLogin?: boolean;
}

const AppRouter = () => {
  const pages: Array<I_Page> = [
    { path: Screens.Home, element: Home },
    { path: Screens.About, element: About },
    { path: Screens.Contacts, element: Contacts },
    { path: Screens.Auth, element: Auth, needIsLogin: false },
    { path: Screens.Profile, element: Profile, needIsLogin: true },
  ];

  return (
    <Routes>
      {pages.map((page, pageIndex) => (
        <Route key={pageIndex} path={page.path} element={<page.element />} />
      ))}
    </Routes>
  );
};

export default AppRouter;
