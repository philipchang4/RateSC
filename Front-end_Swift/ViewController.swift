//
//  ViewController.swift
//  RateSC2.0
//
//  Created by Susan Jensen on 4/21/20.
//  Copyright © 2020 USC. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    //MARK: Properties
    
    @IBOutlet weak var nameItem: UILabel!
    
    @IBAction func setDefaultLabelText(_ sender: UIButton) {
        nameItem.text = "Default Text"
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
