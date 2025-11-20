import { Routes } from '@angular/router';
import { LoginComponent } from './components/login-component/login-component';
import { RegisterComponent } from './components/register-component/register-component';
import { PowerbiComponent } from './components/powerbi-component/powerbi-component';
import { DonantePanel } from './components/donante-panel/donante-panel';
import { ReceptorPanel } from './components/receptor-panel/receptor-panel';

export const routes: Routes = [

    { path: '', redirectTo: 'login', pathMatch: 'full'},
    { path: 'login', component: LoginComponent},
    { path: 'register', component: RegisterComponent},
    { path: 'powerbi', component: PowerbiComponent},
    { path: 'donante', component: DonantePanel},
    { path: 'receptor', component: ReceptorPanel}

];
