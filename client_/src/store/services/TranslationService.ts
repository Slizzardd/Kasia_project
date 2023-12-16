import { makeAutoObservable } from 'mobx';
import { makePersistable } from 'mobx-persist-store';

import ar from '@/assets/locales/ar.json';
import de from '@/assets/locales/de.json';
import en from '@/assets/locales/en.json';
import es from '@/assets/locales/es.json';
import fr from '@/assets/locales/fr.json';
import hu from '@/assets/locales/hu.json';
import ja from '@/assets/locales/ja.json';
import pl from '@/assets/locales/pl.json';
import ru from '@/assets/locales/ru.json';
import ua from '@/assets/locales/ua.json';
import zh from '@/assets/locales/zh.json';
class TranslationService {
  translations: { [key: string]: { [key: string]: string } } = {
    ar,
    de,
    en,
    es,
    fr,
    hu,
    ja,
    pl,
    ru,
    ua,
    zh,
  };
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
