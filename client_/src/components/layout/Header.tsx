import { observer } from 'mobx-react';
import React, { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';

import ToggleLanguage from '@/components/ui/small/ToggleLanguage';
import ToggleTheme from '@/components/ui/small/ToggleTheme';
import { Screens } from '@/routes/AppRouter';
import { useRootModel } from '@/store/rootModel';

import styles from './Header.module.scss';

const Header = () => {
  const { t } = useRootModel((root) => root.translationService);
  const [clicked, setClicked] = useState(false);
  const { authStore, getActualUser } = useRootModel((root) => root.userModel);

  const pages = [
    { path: Screens.Home, name: t('Home') },
    { path: Screens.About, name: t('About us') },
    { path: Screens.Contacts, name: t('Contacts') },
    { path: Screens.Auth, name: t('Sign in'), needIsLogin: false },
    { path: Screens.Profile, name: t('Profile'), needIsLogin: true },
  ];

  const currentLink = useLocation().pathname;
  const isLogin = authStore && authStore.jwtToken;
  const userInfo = authStore.user;

  useEffect(() => {
    if (authStore) {
      getActualUser();
    }
  }, []);
  return (
    <header className={styles.header}>
      <Link className={styles.header_logo} to={'/'}>
        {t('Kasia')}
      </Link>

      <div
        className={styles.header_menu}
        onClick={() => {
          setClicked(!clicked);
        }}
      >
        {clicked ? (
          <svg
            xmlns="http://www.w3.org/2000/svg"
            version="1.1"
            id="Capa_1"
            width="25px"
            height="25px"
            viewBox="0 0 94.926 94.926"
            xmlSpace="preserve"
          >
            <g>
              <path d="M55.931,47.463L94.306,9.09c0.826-0.827,0.826-2.167,0-2.994L88.833,0.62C88.436,0.224,87.896,0,87.335,0   c-0.562,0-1.101,0.224-1.498,0.62L47.463,38.994L9.089,0.62c-0.795-0.795-2.202-0.794-2.995,0L0.622,6.096   c-0.827,0.827-0.827,2.167,0,2.994l38.374,38.373L0.622,85.836c-0.827,0.827-0.827,2.167,0,2.994l5.473,5.476   c0.397,0.396,0.936,0.62,1.498,0.62s1.1-0.224,1.497-0.62l38.374-38.374l38.374,38.374c0.397,0.396,0.937,0.62,1.498,0.62   s1.101-0.224,1.498-0.62l5.473-5.476c0.826-0.827,0.826-2.167,0-2.994L55.931,47.463z" />
            </g>
          </svg>
        ) : (
          <svg
            xmlns="http://www.w3.org/2000/svg"
            x="0px"
            y="0px"
            width="25"
            height="25"
            viewBox="0 0 50 50"
          >
            <path d="M 0 7.5 L 0 12.5 L 50 12.5 L 50 7.5 Z M 0 22.5 L 0 27.5 L 50 27.5 L 50 22.5 Z M 0 37.5 L 0 42.5 L 50 42.5 L 50 37.5 Z"></path>
          </svg>
        )}
      </div>
      <ul className={clicked ? styles.header_navMenu : styles.header_navMenu_notVisible}>
        {pages.map((item, index) => {
          if (
            item.needIsLogin !== undefined &&
            ((item.needIsLogin && !isLogin) || (!item.needIsLogin && isLogin))
          ) {
            return null;
          }

          return (
            <li key={index} className={`${styles.header_nav_item}`}>
              <Link to={item.path} className={styles.header_navMenu_navLinks}>
                {item.path === Screens.Profile
                  ? `${userInfo?.firstName} ${userInfo?.lastName}`
                  : item.name}
              </Link>
            </li>
          );
        })}

        <ToggleLanguage />
        <ToggleTheme />
      </ul>
    </header>
  );
};

export default observer(Header);
