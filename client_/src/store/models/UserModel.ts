import { makeAutoObservable, runInAction } from 'mobx';
import { makePersistable, stopPersisting } from 'mobx-persist-store';

interface RegisterLoginResponse {
  jwtToken: string;
  user: {
    id: string;
    created: string;
    email: string;
    firstName: string;
    lastName: string;
    role: string;
  };
}

interface GetUserResponse {
  id: string;
  created: string;
  email: string;
  firstName: string;
  lastName: string;
  role: string;
}

interface I_authStore {
  jwtToken: string | null;

  user: {
    id: string;
    created: string;
    email: string;
    firstName: string;
    lastName: string;
    role: string;
  } | null;
}

export default class UserModel {
  authStore: I_authStore = {
    jwtToken: null,
    user: null,
  };

  constructor() {
    makeAutoObservable(this);
    stopPersisting(this);
    makePersistable(this, {
      name: 'UserModel',
      storage: localStorage,
      properties: ['authStore'],
    });
  }

  registration = async (userData: {
    email: string;
    password: string;
    lastName: string;
    firstName: string;
  }) => {
    const response = await fetch('http://localhost:8080/api/v1/auth/registration', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userData),
    });

    const text = await response.text();

    if (response.status !== 200) {
      console.log(text);
      this.authStore.user = null;
      this.authStore.jwtToken = null;
      return { errorInRequest: text };
    }

    const { jwtToken, user }: RegisterLoginResponse = JSON.parse(text);

    runInAction(() => {
      this.authStore.jwtToken = jwtToken;
      this.authStore.user = user;
    });
  };

  getActualUser = async () => {
    const response = await fetch('http://localhost:8080/api/v1/user/getUser', {
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + this.authStore.jwtToken,
      },
    });

    const text = await response.text();

    if (response.status !== 200) {
      console.log(text);
      this.authStore.user = null;
      this.authStore.jwtToken = null;
      return { errorInRequest: text };
    }

    const { email, firstName, lastName, id, created, role }: GetUserResponse =
      JSON.parse(text);

    if (!id || !firstName || !lastName || !email || !created || !role) {
      console.log(
        'Invalid response format: One or more properties are missing or empty.',
      );
      this.authStore.user = null;
      this.authStore.jwtToken = null;
      return { errorInRequest: 'Invalid response format' };
    }

    runInAction(() => {
      this.authStore.user = {
        id: id,
        firstName: firstName,
        lastName: lastName,
        email: email,
        created: created,
        role: role,
      };
    });
  };

  login = async (userData: { email: string; password: string }) => {
    const response = await fetch('http://localhost:8080/api/v1/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userData),
    });
    const text = await response.text();

    if (response.status !== 200) {
      console.log(text);
      this.authStore.user = null;
      this.authStore.jwtToken = null;
      return { errorInRequest: text };
    }

    const { jwtToken, user }: RegisterLoginResponse = JSON.parse(text);

    runInAction(() => {
      this.authStore.jwtToken = jwtToken;
      this.authStore.user = user;
    });
  };
}
