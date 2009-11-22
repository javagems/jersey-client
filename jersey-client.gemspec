# Generated by jeweler
# DO NOT EDIT THIS FILE DIRECTLY
# Instead, edit Jeweler::Tasks in Rakefile, and run the gemspec command
# -*- encoding: utf-8 -*-

Gem::Specification.new do |s|
  s.name = %q{jersey-client}
  s.version = "1.0.3"

  s.required_rubygems_version = Gem::Requirement.new(">= 0") if s.respond_to? :required_rubygems_version=
  s.authors = ["pending/unknown"]
  s.date = %q{2009-11-22}
  s.description = %q{Jersey is the open source (under dual CDDL+GPL license) JAX-RS (JSR 311)
        production quality Reference Implementation for building
        RESTful Web services.}
  s.email = %q{autobuild@javagems.org}
  s.homepage = %q{http://www.javagems.org/}
  s.rdoc_options = ["--charset=UTF-8"]
  s.require_paths = ["lib"]
  s.rubygems_version = %q{1.3.5}
  s.summary = %q{A JavaGem version of jersey-client - packaged by JavaGems (http://www.javagems.org) - original by Sun Microsystems, Inc (http://www.sun.com/)}

  if s.respond_to? :specification_version then
    current_version = Gem::Specification::CURRENT_SPECIFICATION_VERSION
    s.specification_version = 3

    if Gem::Version.new(Gem::RubyGemsVersion) >= Gem::Version.new('1.2.0') then
      s.add_development_dependency(%q<junit>, ["~> 3.8.1"])
      s.add_development_dependency(%q<http>, ["~> 20070405"])
      s.add_development_dependency(%q<jersey-core>, ["~> 1.0.3"])
    else
      s.add_dependency(%q<junit>, ["~> 3.8.1"])
      s.add_dependency(%q<http>, ["~> 20070405"])
      s.add_dependency(%q<jersey-core>, ["~> 1.0.3"])
    end
  else
    s.add_dependency(%q<junit>, ["~> 3.8.1"])
    s.add_dependency(%q<http>, ["~> 20070405"])
    s.add_dependency(%q<jersey-core>, ["~> 1.0.3"])
  end
end

