import { observer } from 'mobx-react';
import React, { useState } from 'react';

import MainLayout from '@/components/layout/MainLayout';
import { useRootModel } from '@/store/rootModel';

const Profile = () => {
  const { updateUser, user } = useRootModel((root) => root.userModel);

  const [value, setValue] = useState('');

  const [status, setStatus] = useState<'await' | 'loading' | 'error' | 'success'>(
    'await',
  );

  const updateUserHandler = () => {
    setStatus('loading');
    updateUser({ username: value })
      .then(() => {
        setStatus('success');
      })
      .catch((err: { errorInRequest: string }) => {
        setStatus('error');
      });
  };

  return (
    <MainLayout>
      <p>profile page: {user}</p>

      <input value={value} onChange={(e) => setValue(e.target.value)} />

      <button onClick={updateUserHandler}>change with mobx</button>

      <p>{status}</p>
    </MainLayout>
  );
};

export default observer(Profile);
