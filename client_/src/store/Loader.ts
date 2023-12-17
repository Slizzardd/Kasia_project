import { makeAutoObservable, runInAction } from 'mobx';

import { RootModel } from '@/store/rootModel';

export enum LoaderStatus {
  LOADING = 'LOADING',
  SUCCESS = 'SUCCESS',
  ERROR = 'ERROR',
}

enum Stages {
  TRANSLATION_SERVICE = 'TRANSLATION_SERVICE',

  FINAL = 'FINAL',
}

class Loader {
  status: LoaderStatus = LoaderStatus.LOADING;
  percent: number = 0;
  progress!: { [keys in Stages]: number };
  initLoading!: number;
  statusErrorText: string = '';

  constructor(private readonly rootModel: RootModel) {
    makeAutoObservable(this);

    this.progress = {
      TRANSLATION_SERVICE: 95,
      FINAL: 100,
    };
  }

  load = async () => {
    this.initLoading = new Date(Date.now()).getTime();
    try {
      // TRANSLATION_SERVICE
      await this.loadStage(
        Stages.TRANSLATION_SERVICE,
        this.rootModel.translationService.init,
      );
      // FINAL
      await this.loadStage(Stages.FINAL, this.final);
      //
    } catch (e) {
      console.log(`[Loader]: error when loading: ${e}`);
      runInAction(() => {
        this.statusErrorText = `${e}`;
        this.status = LoaderStatus.ERROR;
      });
    }
  };

  private loadStage = async (stage: Stages, init: () => Promise<void>) => {
    const startLoading = new Date(Date.now()).getTime();

    await init();
    runInAction(() => {
      this.percent = this.progress[stage];
    });

    const endLoading = new Date(Date.now()).getTime();
    console.log(
      `[Loader]: [${this.percent}/100] : ${stage} - ${endLoading - startLoading}ms`,
    );
  };

  private final = (): Promise<void> => {
    return new Promise((resolve) => {
      const endLoading = new Date(Date.now()).getTime();
      const loadingTime = endLoading - this.initLoading;
      const targetLoading = 500;

      if (loadingTime > targetLoading) {
        console.log(`[Loader]: end of loading - ${loadingTime}ms`);
        runInAction(() => {
          this.status = LoaderStatus.SUCCESS;
        });
        resolve();
      } else {
        console.log(`[Loader]: end of loading - ${loadingTime}ms`);

        setTimeout(() => {
          resolve();

          setTimeout(() => {
            runInAction(() => {
              this.status = LoaderStatus.SUCCESS;
            });
          }, 500);
        }, targetLoading - loadingTime);
      }
    });
  };
}

export default Loader;
