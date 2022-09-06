import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { UserResponse } from 'src/app/interfaces/user-response';
import { UserService } from 'src/app/services/UserService/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users!: MatTableDataSource<UserResponse>;
  columnsToDisplay: string[] = [
    'No.',
    'Name',
    'Email',
    'PhoneNumber',
    'Address',
    'UserType',
    'Points',
    'Buttons'
  ];
  constructor(private userService : UserService) { 
    this.users = new MatTableDataSource<UserResponse>();
  }

  ngOnInit(): void {
    this.userService.getAllUndeleted().subscribe((data)=>{
      this.users.data = data;
    });
  }

  deleteUser(id : string){
    this.userService.deleteUser(id).subscribe((data)=>{
      this.users.data = [];
      this.users.data = data;
    });

  }

}
