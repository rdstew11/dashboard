import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConnectSpotifyButtonComponent } from './connect-spotify-button.component';

describe('ConnectSpotifyButtonComponent', () => {
  let component: ConnectSpotifyButtonComponent;
  let fixture: ComponentFixture<ConnectSpotifyButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConnectSpotifyButtonComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConnectSpotifyButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
