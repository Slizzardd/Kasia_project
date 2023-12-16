import { makeAutoObservable, runInAction } from 'mobx';

export default class UserModel {
  user = {
    email: '',
    password: '',
    firstName: '',
    lastName: '',
  };
  constructor() {
    makeAutoObservable(this);
  }

  registration = async (userData: {
    email: string;
    password: string;
    lastName: string;
    firstName: string;
  }) => {
    const request = await fetch('http://localhost:8080/api/v1/auth/registration', {
      method: 'POST',
      body: JSON.stringify(userData),
    });

    const text = await request.text();

    if (request.status !== 200) {
      console.log(text);
      return { errorInRequest: text };
    }

    const newUser = JSON.parse(text) as {
      email: string;
      password: string;
      firstName: string;
      lastName: string;
    };

    runInAction(() => {
      this.user = newUser;
    });
  };

  login = async (userData: { email: string; password: string }) => {
    const request = await fetch('', {});
  };
}
