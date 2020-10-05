import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Transactions} from '../passbook.service';

@Component({
  selector: 'response',
  templateUrl: './response.component.html',
  styleUrls: ['./response.component.css']
})
export class ResponseComponent implements OnInit {
  public Id;
  public transacs: Transactions[];

  constructor(private route:ActivatedRoute ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe( params => {
      this.Id=params.accountId;
      this.transacs = JSON.parse(params.transactions);
    });
  }

}
