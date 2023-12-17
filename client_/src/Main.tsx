import { observer } from 'mobx-react';
import React, { useEffect, useLayoutEffect } from 'react';

import Header from '@/components/layout/Header';
import LoadingProgress from '@/components/ui/LoadingProgress';
import AppRouter from '@/routes/AppRouter';
import { LoaderStatus } from '@/store/Loader';
import { useRootModel } from '@/store/rootModel';
import { initTheme } from '@/utils/Theme';

import styles from './Main.module.scss';

const Main = () => {
  const { load, percent, status, statusErrorText } = useRootModel((root) => root.loader);

  switch (status) {
    case LoaderStatus.LOADING: {
      return (
        <section className={styles.sectionFull}>
          <LoadingProgress
            progress={percent}
            load={load}
            loadingText={'Loading application'}
          />
        </section>
      );
    }
    case LoaderStatus.ERROR: {
      return (
        <section className={styles.sectionFull}>
          <h1 className={styles.errorText}>Error while loading application</h1>

          <p>{statusErrorText}</p>
        </section>
      );
    }
    case LoaderStatus.SUCCESS: {
      return (
        <>
          <Header />
          <AppRouter />
        </>
      );
    }
    default: {
      return null;
    }
  }
};

export default observer(Main);
