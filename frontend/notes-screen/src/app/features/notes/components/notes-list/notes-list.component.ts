import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NoteFilters } from '@notes/models/NoteFilters';
import { NotesService } from '@notes/services/notes.service';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-notes-list',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatTableModule,
    MatPaginatorModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './notes-list.component.html',
  styleUrl: './notes-list.component.css'
})
export class NotesListComponent implements OnInit{

  displayedColumns: string[] = ['site', 'equipment', 'message', 'timestamp'];
  notes: any[] = [];
  filters: NoteFilters = {
    page: 0,
    size: 10
  };

  constructor(private notesService: NotesService) {}

  ngOnInit() {
    this.search();
  }

  search() {
    const params = {
      ...this.filters,
      startDate: this.filters.startDate ? this.formatDate(this.filters.startDate) : '',
      endDate: this.filters.endDate ? this.formatDate(this.filters.endDate) : ''
    }
    this.notesService.notes(params).subscribe({
      next: (data) => {
        // Se o seu backend retorna Page (Spring Data), use data.content
        this.notes = Array.isArray(data) ? data : (data.content || []);
        console.log('Notas carregadas:', this.notes.length);
      },
      error: (e) => console.error('Erro ao buscar notas', e)
    });
  }

  nextPage() {
    if (this.notes.length === this.filters.size) {
      this.filters.page++;
      this.search();
    }
  }

  prevPage() {
    if (this.filters.page > 0) {
      this.filters.page--;
      this.search();
    }
  }

  private formatDate(date: any) {
    return new Date(date).toISOString().split('T')[0];
  }

  formatDisplayDate(dateValue: any): string {
  if (!dateValue) return '--';

  const date = new Date(dateValue);

  // Verifica se a data é inválida (Invalid Date)
  if (isNaN(date.getTime())) {
    return dateValue; // Retorna o texto original se não conseguir converter
  }

  // Formatação manual simples ou você pode usar o DatePipe via código
  return date.toLocaleString('pt-BR', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit'
  });
}

}
