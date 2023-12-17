import { makeAutoObservable, runInAction } from 'mobx';
import { makePersistable, stopPersisting } from 'mobx-persist-store';

export default class CourseModel {
  constructor() {
    makeAutoObservable(this);
  }
}
