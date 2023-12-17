import { makeAutoObservable, runInAction } from 'mobx';
import { makePersistable, stopPersisting } from 'mobx-persist-store';

import { useRootModel } from '@/store/rootModel';

interface Course {
  id: string;
  created: string;
  updated: string;
  numOfLessons: number;
  teacherFirstName: string;
  teacherLastName: string;
  pathToImage: string;
  mainVersion: CourseDescription;
  englishVersion: CourseDescription;
}

interface CourseDescription {
  title: string;
  description: string;
  languages: string;
}

interface I_courseStore {
  courses: Course[];
}

export default class CourseModel {
  constructor() {
    makeAutoObservable(this);
  }

  courses: I_courseStore = {
    courses: [],
  };

  getCourses = async (currentLocale: string | null) => {
    try {
      const url = `http://localhost:8080/api/v1/course/findAll?lang=${currentLocale}`;
      const response = await fetch(url, {
        method: 'GET',
      });

      const text = await response.text();

      if (response.status !== 200) {
        console.error(text);
        return { errorInRequest: text };
      }

      const courses: Array<Course> = JSON.parse(text);

      runInAction(() => {
        this.courses.courses = courses;
      });
    } catch (error) {
      console.error('Error fetching courses:', error);
    }
  };
}
