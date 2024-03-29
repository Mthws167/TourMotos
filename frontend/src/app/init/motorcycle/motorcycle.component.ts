import {Component, OnInit} from '@angular/core';
import {MotorcycleService} from './motorcycle.service';
import {SessionStorage} from "../../../SessionStorage";
import {UserProfileService} from "../../user-profile/user-profile.service";
import Swal from "sweetalert2";

@Component({
  selector: 'motorcycle',
  templateUrl: './motorcycle.component.html',
  styleUrls: ['./motorcycle.component.css']
})

export class MotorcycleComponent extends SessionStorage implements OnInit {
  moto: Moto;
  motociclista: Motociclista;
  existeMoto: boolean;
  motos: Moto[] = []; // Adicione um array de motos

  constructor(private motorcycleService: MotorcycleService,
              private userProfileService: UserProfileService) {
    super();
  }

  ngOnInit() {
    if (this.getSession() == null || this.getSession() == undefined) {
      this.alertErrorAuth();
      window.location.href = 'http://localhost:4200/#';
    } else {
      this.motociclista = JSON.parse(sessionStorage.getItem('motociclista'));
      this.moto = {
        id: undefined,
        modelo: '',
        marca: ''

      };
      this.verificaSeExisteMoto(this.motociclista);
    }
  }

  alertErrorAuth() {
    Swal.fire({
      title: 'Precisa de autorização!',
      icon: 'error',
      position: 'top-end',
      showConfirmButton: false,
      timer: 1000
    });
  }

  reload() {
    window.location.reload();
  }

  verificaSeExisteMoto(motociclista: Motociclista) {
    if (motociclista.moto != null) {
      this.existeMoto = true;
      this.motos.push(motociclista.moto); // Adicione a moto ao array de motos
    } else {
      this.existeMoto = false;
    }
  }

  alertAddSuccess() {
    Swal.fire({
      title: 'Motocicleta cadastrada com sucesso!',
      icon: 'success',
      position: 'top-end',
      showConfirmButton: false,
      timer: 1000
    });
  }

  alertAddError(title?: string) {
    if (!title) {
      Swal.fire({
        title: 'Erro ao cadastrar motocicleta!',
        icon: 'error',
        position: 'top-end',
        showConfirmButton: false,
        timer: 4000
      });
    } else {
      Swal.fire({
        title: `${title}`,
        icon: 'error',
        position: 'top-end',
        showConfirmButton: false,
        timer: 4000
      });
    }
  }

  alertExcludeSuccess() {
    Swal.fire({
      title: 'Motocicleta cadastrada com sucesso!',
      icon: 'success',
      position: 'top-end',
      showConfirmButton: false,
      timer: 1000
    });
  }

  alertExcludeError() {
    Swal.fire({
      title: 'Erro ao excluir motocicleta!',
      icon: 'error',
      position: 'top-end',
      showConfirmButton: false,
      timer: 1000
    });
  }

  cadastrarMoto(moto: Moto) {
    try {
      if (moto.marca != '' && moto.modelo != '') {
        this.motorcycleService.cadastrarMoto(moto).then(value => {
          this.alertAddSuccess();
          this.motociclista.moto = value.data;
          this.userProfileService.editarMotociclista(this.motociclista).then(value1 => {
            this.session(value1.data);
            this.motociclista = JSON.parse(sessionStorage.getItem('motociclista'));
            this.motos.push(this.motociclista.moto);
          }).catch(reason => {
            this.alertAddError();
          });
        }).catch(reason => {
          this.alertAddError();
        });
      } else {
        this.alertAddError('Preencha os campos corretamente!');
      }
    } catch (e) {
      this.alertAddError();
    }
  }

  excluirMoto(moto: Moto) {
    try {
      if (moto != null) {
        this.motorcycleService.excluirMoto(moto.id).then(value => {
          this.alertExcludeSuccess();
          this.motos.splice(0, 1);
          this.motociclista.moto = null;
          this.userProfileService.editarMotociclista(this.motociclista).then(value1 => {
            this.session(value1.data);
            this.motociclista = JSON.parse(sessionStorage.getItem('motociclista'));
          });
        }).catch(reason => {
          this.alertExcludeError();
        });
      }
    } catch (e) {
      this.alertExcludeError();
    }
  }

}