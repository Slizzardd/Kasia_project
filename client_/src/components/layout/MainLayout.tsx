import React from 'react';

import styles from './MainLayout.module.scss';

const MainLayout: React.FC<{ children: React.ReactNode; className?: string }> = (
  props,
) => {
  if (props.className !== undefined) {
    return (
      <main className={props.className}>
        <div className={styles.main}>{props.children}</div>
      </main>
    );
  } else {
    return <main className={styles.main}>{props.children}</main>;
  }
};

export default MainLayout;
