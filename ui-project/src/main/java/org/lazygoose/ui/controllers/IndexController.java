/**
 * 
 */
package org.lazygoose.ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Deepak N
 *
 */

@Controller
public class IndexController {

	@RequestMapping("/")
    public String index(final Model model) {
//        model.addAttribute("customers", Collections.emptyList());
        return "index";
    }
}
