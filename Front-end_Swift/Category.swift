//
//  Category.swift
//  RateSC2.0
//
//  Created by Susan Jensen on 4/18/20.
//  Copyright © 2020 USC. All rights reserved.
//

import UIKit

class Category: NSObject
{
    //var cat: String
    var name: String
    var rating: String
    var descrip: String
    //var image: UIImage
    init(name: String, rating:String, descrip:String)//, image:UIImage )
    {
        self.name = name
        self.rating = rating
        self.descrip = descrip
        //self.image = image
    }
}
