import { Component, OnInit } from '@angular/core';
import { DoctorService } from '../doctor.service';

@Component({
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.css']
})
export class PatientsComponent implements OnInit {
  patients: any[] = [];  
  filteredPatients: any[] = [];  // Stores filtered results
  selectedPatient: any = null;  
  searchTerm: string = '';  

  constructor(private doctorService: DoctorService) {}

  ngOnInit(): void {
    this.loadPatients();
  }

  // ✅ Fetch Patients from Backend API
  loadPatients(): void {
    this.doctorService.getPatients().subscribe(
      (data) => {
        this.patients = data;
        this.filteredPatients = [...this.patients]; // Ensures deep copy
      },
      (error) => {
        console.error('❌ Error fetching patients:', error);
      }
    );
  }

 
  searchPatients(): void {
    this.filteredPatients = this.patients.filter((patient) =>
      patient.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  
  viewPatient(patient: any): void {
    this.selectedPatient = patient;
  }


  closeModal(): void {
    this.selectedPatient = null;
  }

  
}
