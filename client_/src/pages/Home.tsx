import { observer } from 'mobx-react';
import React, { useEffect, useState } from 'react';

import MainLayout from '@/components/layout/MainLayout';
import { useRootModel } from '@/store/rootModel';

import styles from './Home.module.scss';

const Home = () => {
  const { courses, getCourses } = useRootModel((root) => root.courseModel);
  const { t, currentLocale } = useRootModel((root) => root.translationService);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchData = async () => {
      try {
        await getCourses(currentLocale);
      } catch (error) {
        console.error('Error fetching courses:', error);
        setError('Error fetching courses. Please try again');
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [getCourses, currentLocale]);

  return (
    <MainLayout>
      {loading ? (
        <div>Loading...</div>
      ) : error ? (
        <div>{error}</div>
      ) : (
        <section>
          <ul className={styles.wrapper}>
            {courses.courses.map((course) => (
              <li key={course.id}>
                <div className={styles.wrapper_card}>
                  <img
                    src={'src/assets/images/glory05-300x200.jpg'}
                    className={styles.wrapper_card_image}
                    alt={`Course: ${course.mainVersion.title}`}
                  />
                  <h2 className={styles.wrapper_card_title}>
                    {t('Title')}: {course.mainVersion.title}
                  </h2>
                  <h2 className={styles.wrapper_card_title}>
                    Language: {course.mainVersion.languages}
                  </h2>
                  {course.numOfLessons > 0 && (
                    <h3 className={styles.wrapper_card_description}>
                      Number of levels: {course.numOfLessons}
                    </h3>
                  )}
                  <h3 className={styles.wrapper_card_owner}>
                    {t('Teacher')}: {course.teacherFirstName} {course.teacherLastName}
                  </h3>
                  {course.mainVersion.description && (
                    <p className={styles.wrapper_card_description}>
                      {t('Description')}: {course.mainVersion.description}
                    </p>
                  )}
                  <button className={styles.wrapper_card_button}>{t('Details')}</button>
                </div>
              </li>
            ))}
          </ul>
        </section>
      )}
    </MainLayout>
  );
};

export default observer(Home);
