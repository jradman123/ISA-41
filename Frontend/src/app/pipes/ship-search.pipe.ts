import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'shipSearch'
})
export class ShipSearchPipe implements PipeTransform {

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

          d.numberOfEngine.toLowerCase()
            .indexOf(search.toLowerCase()) > -1 ||

          d.strengthOfEngine.toLowerCase()
            .indexOf(search.toLowerCase()) > -1 ||

          d.maxSpeed.toLowerCase()
            .indexOf(search.toLowerCase()) > -1 ||



          d.description.toLowerCase()
            .indexOf(search.toLowerCase()) > -1
      );


    return searchList;
  }

}
