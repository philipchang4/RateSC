//
//  FirstViewController.swift
//  RateSC2.0
//
//  Created by Susan Jensen on 4/8/20.
//  Copyright © 2020 USC. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController {

    override func viewDidLoad()
    {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
        
    }


}

class FoodCell: UITableViewCell {
    @IBOutlet var name : UILabel?
    @IBOutlet var plantDescription : UILabel?
    @IBOutlet var picture : UIImageView?
}
