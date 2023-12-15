import { makeAutoObservable, runInAction } from 'mobx';

class UserModel {
  user: string;

  constructor() {
    makeAutoObservable(this);
    this.user = 'username';
  }

  updateUser = async (userData: { username: string }) => {
    const request = await fetch('http://localhost:8008/updateUser', {
      method: 'POST',
      body: JSON.stringify(userData),
    });

    const text = await request.text();

    if (request.status !== 200) {
      console.log(text);
      return { errorInRequest: text };
    }

    const newUser = JSON.parse(text) as { username: string };

    runInAction(() => {
      this.user = newUser.username;
    });
  };
}

export default UserModel;
