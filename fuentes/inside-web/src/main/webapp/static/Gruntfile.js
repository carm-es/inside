module.exports = function(grunt) {
    grunt.initConfig({
        pkg : grunt.file.readJSON('package.json'),
        pkg2 : grunt.file.readJSON('package2.json'),
        dirs : {
            dest : 'public',
        },
        watch : {
            sass : {
                files : [ 'sass/**/*.{scss,sass}', 'sass/_partials/**/*.{scss,sass}' ],
                tasks : [ 'sass:dist' ]
            },
            livereload : {
                files : [ '*.html', '*.php', 'public/js/**/*.{js,json}', 'public/css/*.css',
                        'img/**/*.{png,jpg,jpeg,gif,webp,svg}' ],
                options : {
                    livereload : true
                }
            }
        },
        webfont : {
            icons : {
                src : 'icons/**/*.svg',
                dest : 'public/fonts',
                destCss : 'sass/icons/',
                options : {
                    autoHint : false,
                    destHtml : 'icons',
                    embed : true,
                    font : 'morfont',
                    htmlDemoTemplate : 'templates/demo.html',
                    relativeFontPath : '#{$morfos-fonts-folder}',
                    stylesheet : 'scss',
                    syntax : 'bem',
                    template : 'templates/icons-template.css',
                    templateOptions : {
                        baseClass : 'mf-icon',
                        classPrefix : 'mf-icon-',
                        mixinPrefix : 'glyph-'
                    },
                    types : 'eot,woff,ttf,svg',
                    hashes : false
                }
            }
        },
        sass : {
            dist : {
                files : {
                    '<%= dirs.dest %>/css/styles.css' : 'sass/styles.scss',
                    '<%= dirs.dest %>/css/icons.css' : 'sass/icons.scss'
                },
                options : {
                    includePaths : [ '<%= pkg2.folder %>' ],
                    sourceComments : true,
                    sourceMapContents : true
                }
            },
            prod : {
                files : {
                    '<%= dirs.dest %>/css/styles.css' : 'sass/styles.scss',
                    '<%= dirs.dest %>/css/icons.css' : 'sass/icons.scss'
                },
                options : {
                    includePaths : [ '<%= pkg2.folder %>' ],
                    outputStyle : 'compressed'
                }
            }
        }
    });
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-sass');
    grunt.loadNpmTasks('grunt-webfont');

    grunt.registerTask('release', [ 'sass:prod' ]);
    grunt.registerTask('dist', [ 'webfont', 'sass:dist' ]);
    grunt.registerTask('default', [ 'watch' ]);
};