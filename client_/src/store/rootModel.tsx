import React, { createContext, useContext } from 'react';

import Loader from '@/store/Loader';
import CourseModel from '@/store/models/CourseModel';
import UserModel from '@/store/models/UserModel';
import TranslationService from '@/store/services/TranslationService';

class RootModel {
  loader!: Loader;
  translationService!: TranslationService;
  userModel!: UserModel;
  courseModel!: CourseModel;

  constructor() {
    this.translationService = new TranslationService();
    this.userModel = new UserModel();
    this.courseModel = new CourseModel();

    this.loader = new Loader(this);
  }
}

let rootModel = new RootModel();

const refreshRootModel = () => {
  rootModel = new RootModel();
};

const RootModelContext = createContext<RootModel>(rootModel);

const RootModelContextProvider: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  return (
    <RootModelContext.Provider value={rootModel}>{children}</RootModelContext.Provider>
  );
};

function useRootModel<Result>(selector: (value: RootModel) => Result) {
  const store = useContext(RootModelContext);

  if (store === undefined || store === null) {
    throw new Error('Can not `useFeatures` outside of the `RootModelContextProvider`');
  }

  return selector(store);
}

function useTranslate() {
  const { t, changeLocale, getLocale, currentLocale } = useRootModel(
    (root) => root.translationService,
  );

  return { t, changeLocale, getLocale, currentLocale };
}

export {
  refreshRootModel,
  RootModel,
  RootModelContextProvider,
  useRootModel,
  useTranslate,
};
