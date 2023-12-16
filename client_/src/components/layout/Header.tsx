import { observer } from 'mobx-react';
import React, { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';

import ToggleLanguage from '@/components/ui/ToggleLanguage';
import ToggleTheme from '@/components/ui/ToggleTheme';
import { Screens } from '@/routes/AppRouter';
import { useRootModel } from '@/store/rootModel';

import styles from './Header.module.scss';

const Header = () => {
  const { t } = useRootModel((root) => root.translationService);
  const [clicked, setClicked] = useState(false);
  const pages = [
    { path: Screens.Home, name: t('Home') },
    { path: Screens.About, name: t('About us') },
    { path: Screens.Contacts, name: t('Contacts') },
    { path: Screens.Auth, name: t('Sign in'), needIsLogin: false },
    { path: Screens.Profile, name: t('Profile'), needIsLogin: true },
  ];

  const currentLink = useLocation().pathname;
  const isLogin = false;
  const userInfo = { username: 'username' };

  const userLanguage: string = navigator.language;
  return (
    <header className={styles.header}>
      <div className={styles.header_wrapper}>
        <nav className={styles.header_nav}>
          <Link className={styles.header_logotype} to={'/'}>
            {t('Kasia')}
          </Link>
          <div className={styles.header_nav_wrapper}>
            <ul className={styles.header_nav_list}>
              {pages.map((item, index) => {
                const isActive = currentLink === item.path;

                if (
                  item.needIsLogin !== undefined &&
                  ((item.needIsLogin && !isLogin) || (!item.needIsLogin && isLogin))
                ) {
                  return null;
                }

                return (
                  <li key={index} className={`${styles.header_nav_item}`}>
                    <Link to={item.path} className={styles.header_nav_link}>
                      {item.path === Screens.Profile ? userInfo.username : item.name}
                    </Link>
                  </li>
                );
              })}

              {clicked && (
                <div onClick={() => setClicked(!clicked)}>
                  <svg
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <rect width="24" height="4" rx="2" fill="currentColor" />
                    <rect y="10" width="24" height="4" rx="2" fill="currentColor" />
                    <rect y="20" width="24" height="4" rx="2" fill="currentColor" />
                  </svg>
                </div>
              )}
              <ToggleTheme />
              <ToggleLanguage />
            </ul>
          </div>
        </nav>
      </div>
    </header>
  );
};

export default observer(Header);
