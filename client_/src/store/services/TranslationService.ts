import { makeAutoObservable } from 'mobx';
import { makePersistable } from 'mobx-persist-store';

import de from '@/assets/locales/de.json';
import en from '@/assets/locales/en.json';
import ru from '@/assets/locales/ru.json';

class TranslationService {
  translations: { [key: string]: { [key: string]: string } } = { en, ru, de };
  defaultLocale = 'en';
  currentLocale: string | null = null;

  constructor() {
    makeAutoObservable(this);
    makePersistable(this, {
      name: 'TranslationService',
      storage: localStorage,
      properties: [],
    });
  }

  getLocale = () => {
    const TranslationsArray = Object.keys(this.translations);

    if (this.currentLocale !== null && TranslationsArray.includes(this.currentLocale)) {
      return this.currentLocale;
    } else {
      const locale = navigator.language;
      let newLocale = this.defaultLocale;

      for (const translate in TranslationsArray) {
        if (translate.includes(locale)) {
          newLocale = translate;
          break;
        }
      }

      this.currentLocale = newLocale;
      return newLocale;
    }
  };

  changeLocale = (newLocale: string) => {
    const TranslationsArray = Object.keys(this.translations);
    console.log(TranslationsArray, newLocale);
    if (TranslationsArray.includes(newLocale)) {
      this.currentLocale = newLocale;
    } else {
      throw new Error('[changeLocale] - newLocale has undefined in translations object');
    }
  };

  t = (str: string): string => {
    const all = this.translations[this.getLocale()];
    const s = all[str];
    if (s === undefined) {
      return `[not found]: ${s}`;
    } else {
      return s;
    }
  };
}

export default TranslationService;
