//
//  CategoryItemsTableViewCell.swift
//  RateSC2.0
//
//  Created by Susan Jensen on 4/18/20.
//  Copyright © 2020 USC. All rights reserved.
//

import UIKit

class CategoryItemsTableViewCell: UITableViewCell {
    //make properties
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var valueRating: UILabel!
    @IBOutlet weak var nameDescrip: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
