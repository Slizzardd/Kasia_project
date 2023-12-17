import { observer } from 'mobx-react';
import React, { useEffect, useState } from 'react';

import { useTranslate } from '@/store/rootModel';

import styles from './ToggleLanguage.module.scss';
const getDefaultLocale = (str: string, arr: { value: string; label: string }[]) => {
  const d = arr.filter((i) => i.value === str)[0];
  if (d === null || d === undefined) {
    return arr[0];
  } else {
    return d;
  }
};

const ToggleLanguage = () => {
  const { getLocale, changeLocale, currentLocale } = useTranslate();

  const getOptions = () => {
    return [
      { value: 'en', label: 'English' },
      { value: 'ru', label: 'Русский' },
      { value: 'de', label: 'Deutsch' },
      { value: 'ua', label: 'Українська' },
      { value: 'ar', label: 'العربية' },
      { value: 'pl', label: 'Polski' },
      { value: 'es', label: 'Español' },
      { value: 'fr', label: 'Français' },
      { value: 'hu', label: 'Magyar' },
      { value: 'ja', label: '日本語' },
      { value: 'zh', label: '中文' },
      { value: 'cs', label: 'Český' },
    ];
  };

  const [options, setOptions] = useState(getOptions());

  const [selectedOption, setSelectedOption] = useState<{
    value: string;
    label: string;
  } | null>(getDefaultLocale(getLocale(), getOptions()));

  const handleSelect = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const newValue = event.target.value;

    setSelectedOption(getOptions().find((option) => option.value === newValue) || null);

    if (newValue !== null) {
      changeLocale(newValue);
    }
  };

  useEffect(() => {
    setOptions(getOptions());
    setSelectedOption(getDefaultLocale(getLocale(), getOptions()));
  }, [currentLocale, getLocale]);

  return (
    <select
      className={styles.select}
      onChange={handleSelect}
      value={selectedOption?.value}
    >
      {options.map((option, key) => (
        <option key={key} value={option.value}>
          {option.label}
        </option>
      ))}
    </select>
  );
};

export default observer(ToggleLanguage);
