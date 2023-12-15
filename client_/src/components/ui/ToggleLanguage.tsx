import { observer } from 'mobx-react';
import { useEffect, useState } from 'react';
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
const ToggleLanguage = (effect: React.EffectCallback, deps?: React.DependencyList) => {
  const { getLocale, changeLocale, t, currentLocale } = useTranslate();
  const [options, setOptions] = useState([
    { value: 'en', label: t('English') },
    { value: 'ru', label: t('Russian') },
    { value: 'de', label: t('German') },
  ]);

  const [selectedOption, setSelectedOption] = useState<{
    value: string;
    label: string;
  } | null>(getDefaultLocale(getLocale(), options));

  useEffect(() => {
    setOptions([
      { value: 'en', label: t('English') },
      { value: 'ru', label: t('Russian') },
      { value: 'de', label: t('German') },
    ]);
    setSelectedOption(
      getDefaultLocale(getLocale(), [
        { value: 'en', label: t('English') },
        { value: 'ru', label: t('Russian') },
        { value: 'de', label: t('German') },
      ]),
    );
  }, [currentLocale]);
  const locale = getLocale();

  // return <button onClick={toggleLocaleHandler}>{getLocale()}</button>;
  return (
    <Select
      defaultValue={selectedOption}
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
