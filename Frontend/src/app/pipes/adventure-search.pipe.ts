import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'adventureSearch'
})
export class AdventureSearchPipe implements PipeTransform {

  transform(searchList: Array<any>, search: string): any {

    if (searchList && search)
      return searchList.filter(
        (d) =>
          d.name.toLowerCase()
            .indexOf(search.toLowerCase()) > -1 ||

          d.price.toLowerCase()
            .indexOf(search.toLowerCase()) > -1 ||

          d.country.toLowerCase()
            .indexOf(search.toLowerCase()) > -1 ||
          d.city.toLowerCase()
            .indexOf(search.toLowerCase()) > -1 ||

          d.streetName.toLowerCase()
            .indexOf(search.toLowerCase()) > -1 ||

          d.streetNumber.toLowerCase()
            .indexOf(search.toLowerCase()) > -1 ||

          d.description.toLowerCase()
            .indexOf(search.toLowerCase()) > -1
      );


    return searchList;
  }

}
