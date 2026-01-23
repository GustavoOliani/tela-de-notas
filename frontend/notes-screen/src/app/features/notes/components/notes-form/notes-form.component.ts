import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';

import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NotesService } from '@notes/services/notes.service';
import { Note } from '@notes/models/Note';

@Component({
  selector: 'app-notes-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
  ],
  templateUrl: './notes-form.component.html',
  styleUrl: './notes-form.component.css'
})
export class NotesFormComponent implements OnInit {

  // Definimos o FormGroup que controlará o formulário
  notesForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private notesService: NotesService
  ) {}

  ngOnInit(): void {
    this.initForm();
  }

  getControl(name: string): FormControl {
    return this.notesForm.get(name) as FormControl;
  }

  /**
   * Inicializa a estrutura do formulário com validações básicas
   */
  private initForm(): void {
    this.notesForm = this.fb.group({
      site: ['', [Validators.required]],
      equipment: ['', [Validators.required]],
      variable: ['', [Validators.required]],
      author: ['', [Validators.required]],
      message: ['', [Validators.required, Validators.minLength(5)]], // RegEx básico para placa Mercosul
    });
  }

  /**
   * Executado ao clicar em "Register Access"
   */
  createNote(): void {
    if (this.notesForm.valid) {
      // Mapeia os dados do formulário para o modelo
      const newNote: Note = {
        ...this.notesForm.value,
        timestamp: new Date().toISOString() // ver como seguir o modelo
      };

      console.log('Note to be saved:', newNote);
      this.notesService.createNote(newNote).subscribe({
        next: (response) => {
          console.log('Success!', response);
          alert('Nota registrada com sucesso!');
          this.clearForm();
        },
        error: (err) => {
          console.error('Error saving contractor', err);
          if (err.status === 400) alert(`Dados faltando durante a etapa de salvar.`);
          if (err.status === 422) alert(`Regra de negócio violada. Revise as informações`);
          if (err.status === 500) alert(`Erro inesperado ao salvar.`);
        }
      });
    }
  }

  /**
   * Reseta o formulário para o estado inicial
   */
  clearForm(): void {
    this.notesForm.reset();
  }

}
