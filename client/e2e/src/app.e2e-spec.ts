import { AppPage } from './app.po';
import {browser, by, element, protractor} from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();

    expect(page.getParagraphText()).toEqual('Descomplique seu');
  });
});
