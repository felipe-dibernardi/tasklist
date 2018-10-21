import {User} from './user.model';

export class Task {
  id: number;
  title: string;
  description: string;
  status: string;
  creation: Date;
  lastUpdate: Date;
  conclusion: Date;
  removal: Date;
  user: User;
}
