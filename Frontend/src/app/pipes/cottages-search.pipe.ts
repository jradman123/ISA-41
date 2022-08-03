import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'cottagesSearch'
})
export class CottagesSearchPipe implements PipeTransform {

  transform(searchList: Array<any>, search: string): any {

    if (searchList && search)
      return searchList.filter(
        (d) =>
          d.name.toLowerCase()
            .indexOf(search.toLowerCase()) > -1 ||

          d.price.toLowerCase()
            .indexOf(search.toLowerCase()) > -1 ||


          d.description.toLowerCase()
            .indexOf(search.toLowerCase()) > -1
      );


    return searchList;
  }

}

