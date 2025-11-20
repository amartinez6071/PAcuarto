import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-powerbi-component',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './powerbi-component.html',
  styleUrl: './powerbi-component.scss',
})
export class PowerbiComponent {

}
