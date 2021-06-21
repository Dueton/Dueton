import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopVotedComponent } from './top-voted.component';

describe('TopVotedComponent', () => {
  let component: TopVotedComponent;
  let fixture: ComponentFixture<TopVotedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TopVotedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TopVotedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
