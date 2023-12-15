import React from 'react';
import { Link } from 'react-router-dom';

import { Screens } from '@/routes/AppRouter';

const Contacts = () => {
  return (
    <section>
      <p>Contacts page</p>

      <Link to={Screens.About}>about</Link>
    </section>
  );
};

export default Contacts;
