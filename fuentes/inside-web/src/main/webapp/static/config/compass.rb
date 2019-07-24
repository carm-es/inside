# Require any additional compass plugins here.
require 'susy'
require 'breakpoint'

#environment = :production

# Set this to the root of your project when deployed:
http_path = "/"
css_dir = "public/css"
sass_dir = "sass"
images_dir = "public/images"
javascripts_dir = "public/js"

# You can select your preferred output style here (can be overridden via the command line):
# output_style = :expanded or :nested or :compact or :compressed
#output_style = :compressed
output_style = (environment == :production) ? :compressed : :expanded

# To enable relative paths to assets via compass helper functions. Uncomment:
# relative_assets = true

# To disable debugging comments that display the original location of your selectors. Uncomment:
line_comments = (environment == :production) ? :false : :true


# If you prefer the indented syntax, you might want to regenerate this
# project again passing --syntax sass, or you can uncomment this:
# preferred_syntax = :sass
# and then run:
# sass-convert -R --from scss --to sass sass scss && rm -rf sass && mv scss sass

#sass_options = {:debug_info => true}
sass_options = (environment == :production) ? {:debug_info => false} : {:debug_info => true}

# libs import path
add_import_path '../libs/mfsasslib/'