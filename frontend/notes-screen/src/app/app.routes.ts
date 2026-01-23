import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { NotesFormComponent } from '@notes/components/notes-form/notes-form.component';
import { NotesListComponent } from '@notes/components/notes-list/notes-list.component';

export const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'notes', component: NotesFormComponent},
  {path: 'dashboard', component: NotesListComponent},
];
