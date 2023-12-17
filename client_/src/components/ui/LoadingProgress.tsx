import React, { useEffect } from 'react';

import { initTheme } from '@/utils/Theme';

import styles from './LoadingProgress.module.scss';

interface LoadingProgressProps {
  progress: number;
  loadingText?: string;
  load: () => Promise<void>;
}

const LoadingProgress: React.FC<LoadingProgressProps> = (props) => {
  useEffect(() => {
    setTimeout(() => {
      props.load().then(() => {
        initTheme();
      });
    }, 300);
  }, []);

  return (
    <div className={styles.line}>
      {props.loadingText && <p className={styles.line_text}>{props.loadingText}</p>}

      <div className={styles.line_wrapper}>
        <div className={styles.line_inner} style={{ width: `${props.progress}%` }}></div>
      </div>
    </div>
  );
};

export default LoadingProgress;
