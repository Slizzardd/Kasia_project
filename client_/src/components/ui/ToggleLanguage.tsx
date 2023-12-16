import { observer } from 'mobx-react';
import React, { useEffect, useState } from 'react';
import Select from 'react-select';

import { useTranslate } from '@/store/rootModel';

const getDefaultLocale = (str: string, arr: { value: string; label: string }[]) => {
  const d = arr.filter((i) => i.value === str)[0];
  if (d === null || d === undefined) {
    return arr[0];
  } else {
    return d;
  }
};

const ToggleLanguage = () => {
  const { getLocale, changeLocale, t, currentLocale } = useTranslate();

  const getOptions = () => {
    return [
      { value: 'en', label: 'English' },
      { value: 'ru', label: 'Russian' },
      { value: 'de', label: 'Deutsch' },
      { value: 'ua', label: 'Українська' },
      { value: 'ar', label: 'العربية' },
      { value: 'pl', label: 'Polski' },
      { value: 'es', label: 'Español' },
      { value: 'fr', label: 'Français' },
      { value: 'hu', label: 'Magyar' },
      { value: 'ja', label: '日本語' },
      { value: 'zh', label: '中文' },
    ];
  };

  const [options, setOptions] = useState(getOptions());

  const [selectedOption, setSelectedOption] = useState<{
    value: string;
    label: string;
  } | null>(getDefaultLocale(getLocale(), getOptions()));

  useEffect(() => {
    setOptions(getOptions());
    setSelectedOption(getDefaultLocale(getLocale(), getOptions()));
  }, [currentLocale]);

  return (
    <Select
      key={currentLocale}
      value={selectedOption}
      onChange={(newValue) => {
        setSelectedOption(
          newValue ? { value: newValue.value, label: newValue.label } : null,
        );
        if (newValue !== null) {
          changeLocale(newValue.value);
        }
      }}
      options={options}
    />
  );
};

export default observer(ToggleLanguage);
