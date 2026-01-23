import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Note } from "@notes/models/Note";
import { NoteFilters } from "@notes/models/NoteFilters";
import { Observable, of } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class NotesService {

  private readonly API = 'http://localhost:8080/api/v1/notes';

  constructor(private http: HttpClient){ }

  createNote(note: Note): Observable<Array<Note>> {
    const notes: Array<Note> = [];
    notes.push(note);
    return this.http.post<Array<Note>>(this.API, notes);
  }

  notes(filters: NoteFilters): Observable<any> {
    let params = new HttpParams()
      .set('page', filters.page?.toString() || '0')
      .set('size', filters.size?.toString() || '10');

    if (filters.site) params = params.set('site', filters.site.toString());
    if (filters.equipment) params = params.set('equipment', filters.equipment.toString());
    if (filters.startDate) params = params.set('startDate', filters.startDate.toString());
    if (filters.endDate) params = params.set('endDate', filters.endDate.toString());

    return this.http.get<NoteFilters>(this.API, { params });
  }
}
