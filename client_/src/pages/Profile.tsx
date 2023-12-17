import { observer } from 'mobx-react';
import React, { useState } from 'react';

import MainLayout from '@/components/layout/MainLayout';
import { useRootModel } from '@/store/rootModel';

import styles from './Profile.module.scss';
const Profile = () => {
  const { authStore } = useRootModel((root) => root.userModel);
  const { t } = useRootModel((root) => root.translationService);

  const user = authStore.user;
  return (
    <MainLayout>
      <section className={styles.wrapper}>
        <div>
          <h2>{t('Profile')}</h2>
          <div className={styles.wrapper_image}>
            <img
              src={'src/assets/images/glory05-300x200.jpg'}
              className={styles.wrapper_card_image}
              alt={''}
            />
          </div>
          <div className={styles.wrapper_infomation}>
            <div>
              <p>
                {t('Joined')}: {user?.created}
              </p>
            </div>
            <div>
              <p>
                {t('Email')}: {user?.email}
              </p>
            </div>
            <div>
              <p>
                {t('First Name')}: {user?.firstName}
              </p>
            </div>
            <div>
              <p>
                {t('Last Name')}: {user?.lastName}
              </p>
            </div>
            <div>
              <p>Role: {user?.role}</p>
            </div>
          </div>
        </div>
      </section>
    </MainLayout>
  );
};

export default observer(Profile);
